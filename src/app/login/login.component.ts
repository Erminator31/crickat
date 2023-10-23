import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private builder: FormBuilder, private toastr: ToastrService,
    private service: AuthService, private router: Router) {
      sessionStorage.clear();

  }

  userdata: any;

  loginform = this.builder.group({
    username: this.builder.control('', Validators.required),
    password: this.builder.control('', Validators.required)
  })

  proceedlogin() {
    if (this.loginform.valid) {
      //   this.service.Proceedregister(this.loginform.value).subscribe(res => {
      //     this.toastr.success('Please contact admin for enable access','Registered Successfully');
      //     this.router.navigate(['login']);
      //   });

      // } else {
      //   this.toastr.warning('Please enter valid data. The password must contain a capital letter, a symbol, a number and have a lenght of at least 8.');
      this.service.Getbycode(this.loginform.value.username).subscribe(res => {
        this.userdata = res;
        console.log(this.userdata);
        if (this.userdata.password == this.loginform.value.password) {
          if (this.userdata.isactive) {
            sessionStorage.setItem('username', this.userdata.id);
            sessionStorage.setItem('userrole', this.userdata.role);
            this.router.navigate(['']);
          } else {
            this.toastr.warning('The account is inactive. To activate the account contact an admin.');
          }
        } else {
          this.toastr.warning('Please check entered username and password. If you forgot your password contact an admin.');
        }
      });

    }
  }

}
