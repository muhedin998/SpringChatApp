import { Component } from '@angular/core';
import { WebsocketService } from './services/websocket.service';
import {User} from "./models/user";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  data$  = this.webSocketService.data;
  msg:string = ''
  user?: User;
  allUsers?: Observable<User[]> = this.webSocketService.allUsers;
  isConnecting = this.webSocketService.isConnecting;

  constructor(private webSocketService: WebsocketService) {

  }

  ngOnInit() {
    this.data$.subscribe(d => console.log(d))
    this.allUsers?.subscribe(data => console.log(data))
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
  sendPublicMessage() {
    if(this.msg != ''){
      this.webSocketService.sendPublicMessage(this.msg);
      this.msg = ''
    }
  }
}
