import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../../services.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: any
  constructor(private service: ServicesService) { }

  ngOnInit(): void {
    this.showProducts();
  }

  showProducts(){
    this.service.showProducts().subscribe(
      (data)=>{
      
        this.products = data;
      },
      (err)=>{
        console.log(err);
        
      }
    )
  }

}
