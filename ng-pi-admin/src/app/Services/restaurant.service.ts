import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(public http:HttpClient) { }

  getAllRestaurants(){
    return this.http.get<any>('http://35.223.192.95/restaurants/api/v1');
  }
}
