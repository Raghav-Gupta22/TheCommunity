import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  searchQuery: any;
  products: any;
  stats: any;
  constructor(private service: ServicesService, private router: Router) {}
  ngOnInit(): void {
    this.showStats();
  }

  showStats() {
    this.service.showStats().subscribe((data) => {
      console.warn(data);
      this.stats = data;
    });
  }

  addReview(pId: any) {
    localStorage.setItem('reviewProductId', pId);
    this.router.navigate(['addReview']);
  }
  showReview(pId: any) {
    localStorage.setItem('reviewProductId', pId);
    this.router.navigate(['showReview']);
  }

  loggedIn() {
    return this.service.isLogIn();
  }

  logout() {
    this.service.logout();
    this.router.navigate(['login']);
  }

  currentUser() {
    return this.service.getUser();
  }

  removeFilter() {
    localStorage.removeItem('products');
  }

  searchedProducts() {
    const p = localStorage.getItem('products');
    if (p != null) return JSON.parse(p);
    return [];
  }

  OnInput(event: any) {
    this.searchQuery = event.target.value;

    this.service.searchProducts(this.searchQuery).subscribe(
      (data) => {
        this.products = data;
        this.service.sProducts(this.products);
        window.scrollTo(0, 8000);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
