import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-request-review',
  templateUrl: './request-review.component.html',
  styleUrls: ['./request-review.component.css']
})
export class RequestReviewComponent implements OnInit {
  rt = 0;
  reviews: any;
  constructor(private service: ServicesService,private router: Router) { }

  ngOnInit(): void {
  }

  requestReview(form : any){
    let newPost = {
      productName: form.value.productName,
      brand: form.value.brand,
      productCode: form.value.productCode,
    }
    console.log(newPost);
    this.service.reviewRequest(newPost).subscribe(
      (data)=>{ 
        this.reviews = data;
        this.rt = 1;
      },
      (err)=>{console.log(err)}
    )
  }

  logout(){
    this.service.logout();
    this.router.navigate(['login']);
  }

  loggedIn(){
    return this.service.isLogIn();
  }
  currentUser(){
    return this.service.getUser().firstName+" "+this.service.getUser().lastName;
  }

}
