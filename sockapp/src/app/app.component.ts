import { Component } from '@angular/core';
import { WebsocketService } from './services/websocket.service';
import {User} from "./models/user";
import {filter, find, map, Observable, of, take, tap, withLatestFrom} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  data$  = this.webSocketService.data;
  msg:string = ''
  user?: User;
  chatWithUser?: Observable<User>;
  allUsers?: Observable<User[]> = this.webSocketService.allUsers;
  isConnecting = this.webSocketService.isConnecting;

  constructor(private webSocketService: WebsocketService) {

  }

  ngOnInit() {
    this.data$.subscribe(d => console.log(d))
    this.allUsers?.subscribe(data => console.log(data))
    this.chatWithUser?.subscribe(data => console.log(data))
  }

  handleUsername(event:any){
    this.user = {...this.user, firstName:event.target.value}
  }
  handlePhone(event:any){
  this.user = {...this.user, phoneNumber:event.target.value}
}
  registerUser() {
    this.webSocketService.connect(this.user!);
  }
  handleChatMessage (event: any) {
    this.msg = event.target.value;
  }

  chatWith(usrId?: number) {
    this.chatWithUser =  this.allUsers?.pipe(
      take(1),
      map(users => users.filter(user => user.id === usrId)[0])
    )
  }

  sendPublicMessage() {
    if(this.msg != ''){
      this.webSocketService.sendPublicMessage(this.msg);
      this.msg = ''
    }
  }
  sendPrivateMessage() {}
}
