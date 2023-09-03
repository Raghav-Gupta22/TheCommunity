import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { ServicesService } from '../services.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  msg: any;
  user = new User();
  @Input()
  password!: string;
  constructor(private service: ServicesService, private router: Router) { }

  ngOnInit(): void {
  
  }

  registerUser(registerForm: NgForm){
    // if(this.user.cpassword === this.user.cpassword){

    this.service.registerUser(this.user).subscribe(
      data => {console.log("Success!")
     
      this.service.setUser(data);
      this.service.isLoggedIn = true;
      this.router.navigate(['login']);
      },
      err => {console.log("Error!"),
       this.msg = "User already registered with same Email"})
      {
        
      }
    
  }
}
