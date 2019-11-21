import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(public http:HttpClient) { }

  getAllOrders(){
    this.http.get<any>('http://35.238.91.208/orders');
  }

  postOrder(obj:any){
    this.http.post<any>('http://35.238.91.208/orders',obj);
  }

  getAllOrdersByRestaurants(restaurant_id:string){
    this.http.get<any>(`http://35.238.91.208/orders/restaurant/${restaurant_id}`);
  }

  getAllOrdersByUser(user_id:string){
    this.http.get<any>(`http://35.238.91.208/orders/${user_id}`);
  }
}
