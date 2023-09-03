import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../../services.service';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {
  review: any
  approved: any
  constructor(private service: ServicesService) { }

  ngOnInit(): void {
    this.showReviews();
  }

  showReviews(){
    this.service.showAdminReviews().subscribe(
      (data)=>{
       
        this.review = data;
      },
      (err)=>{
        console.log(err);
      }
    )
  }

  approveReview(review: any){
    this.service.approveReview(review).subscribe(
      (data)=>{
        this.approved = data;
        this.showReviews();
      },
      (error)=>{console.log(error);
      }
    )
  }
}
