import { Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements DoCheck{
  
  ismenurerequired=false;
  isadminuser=false;
  constructor(private router:Router, private service:AuthService){
  }
  ngDoCheck(): void {
    let currenturl=this.router.url;
    let role=sessionStorage.getItem('role');
    if(currenturl=='/login' || currenturl=='/register'){
      this.ismenurerequired=false;
    } else{
      this.ismenurerequired=true;
    }

    if(this.service.GetUserrole()==='admin'){
      this.isadminuser=true;
    } else{
      this.isadminuser=false;
    }

  }

}
