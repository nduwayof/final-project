import { Injectable } from "@angular/core";
import { CookieService } from "./cookie.service";
import {jwtDecode} from "jwt-decode"
import { from } from 'rxjs';


@Injectable({
  providedIn: "root"
})
export class TokenService {
  token: any;
  user: any;
  constructor(private _cookieService: CookieService) {
    this.token = this._cookieService.getToken();
    this.user = jwtDecode(this.token);
  }
}
