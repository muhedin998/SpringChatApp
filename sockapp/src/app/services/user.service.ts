import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import * as http from "http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private  http: HttpClient) { }

  getAllUsers():Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/user/all');
  }

  createUser(user:User): void {
    // this.http.post('http://localhost:8080/user/create', user).subscribe(data => console.log(data), (err) => console.log(err));
  }
}
