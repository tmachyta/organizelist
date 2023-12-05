import {Component, ViewChild} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {UserService} from "../../service/user/user.service";
import {AuthService} from "../../service/auth/auth.service";
import {Router, RouterOutlet} from "@angular/router";
import {UserLoginRequest} from "../../model/user/user-login-request";
import {UserLoginResponse} from "../../model/user/user-login-response";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  @ViewChild('loginForm') loginForm!: NgForm;

  constructor(private userService: UserService, private authService: AuthService, private router: Router) {
  }

  login() {
    if (this.loginForm.valid) {
      const request: UserLoginRequest = {
        email: this.email,
        password: this.password,
      };

      console.log('Logging in...');
      this.userService.loginUser(request).subscribe(
        (response: UserLoginResponse) => {
          console.log('Successfully logged in:', response);
          this.authService.saveToken(response.token)
          this.authService.saveUserEmail(request.email);
          this.authService.saveUserId(response.userId)
          this.router.navigate(['api/task-groups/user/email', request.email]);
        },

        (error) => {
          console.error('Login error:', error);
        }
      );
    }
  }
}
