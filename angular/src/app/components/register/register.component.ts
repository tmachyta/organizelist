import {Component, ViewChild} from '@angular/core';
import {UserRegistrationRequest} from "../../model/user/user-registration-request";
import {FormsModule, NgForm} from "@angular/forms";
import {UserService} from "../../service/user/user.service";
import {Router, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registrationRequest: UserRegistrationRequest = new UserRegistrationRequest();

  @ViewChild('registrationForm') registrationForm!: NgForm;

  constructor(private userService: UserService, private router: Router) {}

  register(registerForm: NgForm) {
    if (registerForm.valid) {
      this.userService.registerUser(this.registrationRequest).subscribe(
        (response: any) => {
          console.log('Successfully registered user:', response);
          this.router.navigate(['/api/auth/login']);
        },
        (error) => {
          console.error('Registration error:, error')
        }
      );
    }
  }
}
