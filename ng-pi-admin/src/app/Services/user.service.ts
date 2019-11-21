import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class UserService {
  api: string;
  login_api:string
  constructor(public http: HttpClient) {
    this.api='http://35.223.192.95/users';
    this.login_api = `http://35.223.192.95`;
  }
  getAllUsers() {
    return this.http.get<any>(`${this.api}`);
  }

  registerUser(obj: any) {
    return this.http.post<any>(`${this.api}`, obj);
  }

  loginUser(obj: any) {
    return this.http.post<any>(`${this.login_api}/auth/login`, obj);
  }
}
