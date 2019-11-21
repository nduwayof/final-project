import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(public http : HttpClient) { }
  
  getNearestRestaurants(longitude, latitude){
    return this.http.get<any>(`http://35.223.192.95/locations/nearest?longitude=${longitude}&latitude=${latitude}`);
  }
}
