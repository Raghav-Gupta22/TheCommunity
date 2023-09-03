import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../../services.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  searchQuery: any;
  products: any;
  searchClicked: any;

  constructor(private service: ServicesService, private router: Router) {}
  ngOnInit(): void {}

  loggedIn() {
    return this.service.isLogIn();
  }

  logout() {
    this.service.logout();
    this.router.navigate(['login']);
  }

  currentUser() {
    return (
      this.service.getUser().firstName + ' ' + this.service.getUser().lastName
    );
  }
}
