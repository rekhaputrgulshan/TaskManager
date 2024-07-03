import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user = {
    name: '',
    phoneNumber: '',
    username: '',
    email: '',
    password: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/v1/user/signup', this.user)
      .subscribe(response => {
        console.log('User signed up successfully', response);
        this.router.navigate(['/login']);
      }, error => {
        console.error('Error during signup', error);
      });
  }
}
