import { Component, OnInit } from '@angular/core';
import { TablesDataService } from './tablesData.service';
import { RestaurantService } from '../../../../Services/restaurant.service';
import { LocationService } from '../../../../Services/location.service';
import { GeolocationService } from '../../../../Services/geolocation.service';
import { CookieService } from '../../../../Services/cookie.service';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss'],
  providers: [TablesDataService]
})
export class DataTableComponent implements OnInit {
  tableData: Array<any>;
  restaurantData : Array<any>;
  nearestData : Array<any>

  /* pagination Info */
  pageSize = 12;
  pageNumber = 1;

  constructor(
    private _tablesDataService: TablesDataService,
    private _restaurantService : RestaurantService,
    private _locationService : LocationService,
    private _geolocationService : GeolocationService,
    private _cookieService : CookieService
    ) { }

  ngOnInit() {
    this.loadData();
    this.getRestaurants();
    this.getNearestRestaurants();
  }

  loadData() {
    this.tableData = this._tablesDataService.DATA;
  }

  getRestaurants(){
    this._restaurantService.getAllRestaurants().subscribe((data)=>{
      this.restaurantData = data;
    });
  }

  getNearestRestaurants(){
    const long = this._cookieService.getLong();
    const lat = this._cookieService.getLat();
    console.log(`>>${long} , ${lat}<<`);
    this._locationService.getNearestRestaurants(long,lat).subscribe((data)=>{
      console.log(data);
      this.nearestData = data;
    });
  }

  pageChanged(pN: number): void {
    this.pageNumber = pN;
  }

}
