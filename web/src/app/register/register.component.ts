import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private builder: FormBuilder, private toastr: ToastrService,
    private service: AuthService, private router: Router) {

  }

  registerform = this.builder.group({
    id: this.builder.control('', Validators.compose([Validators.required, Validators.minLength(5)])),
    name: this.builder.control('', Validators.required),
    password: this.builder.control('', Validators.compose([Validators.required])),
    email: this.builder.control('', Validators.compose([Validators.required, Validators.email])),
    gender: this.builder.control('male'),
    role: this.builder.control(''),
    isactive: this.builder.control(false)
  });

  proceedregistration() {
    if (this.registerform.valid) {
      this.service.Proceedregister(this.registerform.value).subscribe(res => {
        this.toastr.success('Please contact admin for enable access','Registered Successfully');
        this.router.navigate(['login']);
      });

    } else {
      this.toastr.warning('Please enter valid data');
    }
  }

}
