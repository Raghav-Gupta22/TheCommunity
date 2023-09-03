import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './models/user';

@Injectable({
  providedIn: 'root',
})
export class ServicesService {
  isLoggedIn = false;

  constructor(private http: HttpClient) {}

  // Some Functions which we used in this application at different files
  //loginUser
  public loginUserToken(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  //is login or not
  public isLogIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

  //logout
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.isLoggedIn = false;
    return true;
  }

  sProducts(products: any) {
    localStorage.setItem('products', JSON.stringify(products));
  }

  //get Token
  public getToken() {
    return localStorage.getItem('token');
  }

  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    }
    this.logout();
    return null;
  }

  //get User role
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }

  // All the API Services

  //generate token
  public generateToken(loginData: any) {
    return this.http.post<any>(
      'http://localhost:3000/generate-token',
      loginData
    );
  }

  //current User
  public getCurrentUser() {
    return this.http.get('http://localhost:3000/current-user');
  }

  //register User
  public registerUser(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:3000/user/register', user);
  }
  //Search product by plain text
  public searchProducts(query: String) {
    return this.http.get('http://localhost:3000/products?query=' + query);
  }

  // show Reviews for a product
  public showReviews(productId: any) {
    return this.http.get(
      'http://localhost:3000/products/' + productId + '/showReviews'
    );
  }

  // Add new review
  public addReview(reviewData: any) {
    return this.http.post('http://localhost:3000/addReview', reviewData);
  }

  //Add new Product from admin
  public addProduct(productData: any) {
    return this.http.post('http://localhost:3000/addProduct', productData);
  }

  // Stats of home page
  public showStats(): Observable<any> {
    return this.http.get<any>('http://localhost:3000/home/stats');
  }

  //show all users in admin
  public showUsers() {
    return this.http.get('http://localhost:3000/user/users');
  }

  //show all producs in admin
  public showProducts() {
    return this.http.get('http://localhost:3000/user/products');
  }

  //show all reviews in user
  public showAllReviews() {
    return this.http.get('http://localhost:3000/user/reviews');
  }

  //show all reviews in admin which are not approved yet
  public showAdminReviews() {
    return this.http.get('http://localhost:3000/admin/reviews');
  }

  // update the review and approve it
  public approveReview(review: any) {
    return this.http.put('http://localhost:3000/review/approve', review);
  }

  //update the user status active or non-active
  public userStatus(user: any) {
    return this.http.put('http://localhost:3000/user/active', user);
  }

  //to request a review
  public reviewRequest(product: any) {
    return this.http.post('http://localhost:3000/review/request', product);
  }
}
