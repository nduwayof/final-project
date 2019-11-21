import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { UserService } from "../Services/user.service";
import { SwalService } from "../Services/swal.service";
import { Router } from '@angular/router';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"]
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  constructor(
    private _formBuilder: FormBuilder,
    private _userService: UserService,
    private _swalService: SwalService,
    private _router : Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this._formBuilder.group({
      name: [""],
      email: ["", Validators.required],
      password: ["", Validators.required],
      password2: ["", [Validators.required]],
      phone: ["", Validators.required],
      country: ["", Validators.required],
      street1: ["", Validators.required],
      street2: ["", Validators.required],
      zip: ["", Validators.required],
      city: ["", Validators.required],
      role: [["user"]]
    });
  }

  onSubmit() {
    const user = this.registerForm.value;
    if (!this.registerForm.value.password || !this.registerForm.value.password2) {
      this._swalService.notify("Failed!", "Please enter Password", "error");
    } 
    else if(!this.registerForm.value.email){
      this._swalService.notify("Failed!", "Please enter email", "error");
    }
    else if(this.registerForm.value.password !== this.registerForm.value.password2){
      this._swalService.notify("Failed!", "Password does not match !", "error");
    }
    
    else {
      this._userService.registerUser(user).subscribe(data => {
        console.log(">>>SAVED USER<<<");
        this._swalService.notify(
          "Good job!",
          `User '${data.name}' Saved Successfully`,
          "success"
        );
        this._router.navigate(['/login']);
      });
    }
  }
}
