import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeolocationService {
  longitude:Number;
  latitude:Number;
  constructor() { }

  setLongitude(long:Number){
    this.longitude = long;
  }

  setLatitude(lat:Number){
    this.latitude=lat;
  }

  getLongitude():Number{
    return this.longitude;
  }

  getLatitude():Number{
    return this.latitude;
  }
}
