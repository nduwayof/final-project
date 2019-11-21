import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { UserService } from "../../Services/user.service";
import { SwalService } from "../../Services/swal.service";
import { CookieService } from "../../Services/cookie.service";
import { Router } from "@angular/router";
import { GeolocationService } from "../../Services/geolocation.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(
    private _formBuilder: FormBuilder,
    private _userService: UserService,
    private _swalService: SwalService,
    private _cookieService: CookieService,
    private _geoLocationService: GeolocationService,
    private _router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this._formBuilder.group({
      email: [""],
      pass: [""]
    });
  }

  onSubmit() {
    navigator.geolocation.getCurrentPosition(data => {
      this._geoLocationService.setLongitude(data.coords.longitude);
      this._geoLocationService.setLatitude(data.coords.latitude);
      this._cookieService.setLong(data.coords.longitude);
      this._cookieService.setLat(data.coords.latitude);
    });
    const user = this.loginForm.value;
    if (!user.email) {
      this._swalService.notify("Failed!", "Please enter username!", "error");
    } else if (!user.pass) {
      this._swalService.notify("Failed!", "Please enter password!", "error");
    } else {
      this._userService.loginUser(user).subscribe(data => {
        if (data.msg !== "Sucessful log in") {
          this._swalService.notify(
            "Failed!",
            "Username/Password incorrect",
            "error"
          );
        } else {
          const token = data.token;
          this._cookieService.setAuth(token);
          this._swalService.notify("Good job!", `Login Succesfull`, "success");
          this._router.navigate(["/pages/index"]);
        }
      });
    }
  }
}
