import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies/ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class CookieService {

  constructor() { }

  getAuth():string{
    return Cookie.get('token');
  }

  setAuth(value:string):void{
    Cookie.set('token',value, 0.0138889);
  }

  deleteAuth(){
    Cookie.delete('token');
  }

  loggedIn(){
    return !!Cookie.get('token');
  }

  getToken(){
    return Cookie.get('token');
  }

  setLong(long:Number){
    Cookie.set('Longitude',long.toString());
  }

  setLat(lat:Number){
    Cookie.set('Latitude',lat.toString());
  }

  getLong(){
    return Cookie.get('Longitude');
  }

  getLat(){
    return Cookie.get('Latitude');
  }
}
