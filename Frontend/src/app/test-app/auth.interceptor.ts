import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ServicesService } from "./services.service";



@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private service: ServicesService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        //add the JWT token which is stored in localStorage
        
        
        let authReq=req;
        const token = this.service.getToken();
        
        if(token!=null){
            authReq = authReq.clone({
                setHeaders:{Authorization:`Bearer ${token}` },
        });
        }
        return next.handle(authReq);
    }

}

export const authInterceptorProviders=[
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi:true,
    },
]