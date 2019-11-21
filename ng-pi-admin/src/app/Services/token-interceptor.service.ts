import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';
import { CookieService } from './cookie.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private _cookieService : CookieService) { }

  intercept(req,next){
    let tokenizedReq = req.clone({
      setHeaders : {
        Authorization : `Bearer ${this._cookieService.getToken()}`
      }
    });
    return next.handle(tokenizedReq);
  }
}
