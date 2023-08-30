import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { of, Observable, map, tap, take, concat, switchMap, BehaviorSubject} from 'rxjs';
import {UserService} from "./user.service";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  allUsers: Observable<User[]> = this.userService.getAllUsers();
  _message: string[] = []
  stompClient: any ;
  isConnecting: BehaviorSubject<boolean> = new BehaviorSubject(false);
  username:string = '';
  data: Observable<any>  = of({
    connected: false,
    username: "",
    recievername: "",
    message: "",
    messages: this._message
  })

  constructor(private userService: UserService) {
    let user = JSON.parse(sessionStorage.getItem('user')!)
    if (user) {
      this.connect(user);
    }
   }
   connect(user: User){
    let sock = new SockJS("http://localhost:8080/ws")
    this.stompClient = Stomp.over(sock);
    this.stompClient.connect({}, this.onConected, this.onError)
     this.handleUsername(user.firstName);
    if(!sessionStorage.getItem('user')) {
      sessionStorage.setItem('user', JSON.stringify(user))
    }
    this.userService.createUser(user);
    this.username = user.phoneNumber!;
    console.log("Connect :/")
   }

    onConected:any = () => {
      this.stompClient.subscribe('/topic/public', this.onPublicMessageRecieved);

      // Tell your username to the server
      this.stompClient.send("/app/chat.addUser",
          {},
          JSON.stringify({sender: this.username, type: 'JOIN'})
      )
      this.isConnecting.next(true);
   }

   setConnectedTrue(): void {
     this.data.pipe(
       take(1),
       map(data => data = {...data, connected: true})
     ).subscribe(d => console.log(d));
   }

   onPublicMessageRecieved:any = (payload:any) => {
       let payloadData = JSON.parse(payload.body);
    this._message.push(payloadData);
    console.log('***' + payload + '****')
  }

   onError(err:any) {
    console.log(err);
   }

   handleUsername(event:any){
    this.data.pipe(
      take(1),
      map(data => data = {...data, username: event})
    ).subscribe(d => console.log(d));
  }
  sendPublicMessage(msg: string) {
    let chatMeesage = {
      sender: {
        id: 1,
        phoneNumber: this.username,
        firstName: 'Muhedind',
        lastName: 'Alic'
      },
      content: msg,
      type: 'CHAT'
    }

    if(this.stompClient) {
      this.stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMeesage));
    }

  }

  sendPrivateMessgae(msg: string, reciever: User) {
    let chatMeesage = {
      sender: {
        id: 4,
        phoneNumber: 2345355,
        firstName: 'Mikica',
        lastName: 'Springel'
      },
      content: msg,
      type: 'CHAT'
    }

    if(this.stompClient) {
      this.stompClient.send("/app/private", {}, JSON.stringify(chatMeesage));
    }

  }
}
