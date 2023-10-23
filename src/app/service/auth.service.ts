import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }
  apiurl='https://dry-caverns-85169-185a985df7fb.herokuapp.com/api/v1.0/users/all';

  GetAll() {
    return this.http.get(this.apiurl);
  }

  Getbycode(code:any) {
    return this.http.get(this.apiurl+'/'+code);
  }

  GetAllRole() {
    return this.http.get('http://localhost:3000/role');
  }

  Proceedregister(inputdata:any){
    return this.http.post(this.apiurl, inputdata);
  }

  Updateuser(code:any, inputdata: any) {
    return this.http.put(this.apiurl+'/'+code, inputdata);
  }

  IsloggedIn(){
    return sessionStorage.getItem('username')!=null;
  }

  GetUserrole(){
    return sessionStorage.getItem('userrole')!=null?sessionStorage.getItem('userrole')?.toString():'';
  }

  Deleteuser(userId: string) {
    return this.http.delete(`${this.apiurl}/${userId}`);
  }

}
