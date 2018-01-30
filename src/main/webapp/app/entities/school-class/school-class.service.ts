import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SchoolClass } from './school-class.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SchoolClassService {

    private resourceUrl =  SERVER_API_URL + 'api/school-classes';

    constructor(private http: Http) { }

    create(schoolClass: SchoolClass): Observable<SchoolClass> {
        const copy = this.convert(schoolClass);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(schoolClass: SchoolClass): Observable<SchoolClass> {
        const copy = this.convert(schoolClass);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SchoolClass> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to SchoolClass.
     */
    private convertItemFromServer(json: any): SchoolClass {
        const entity: SchoolClass = Object.assign(new SchoolClass(), json);
        return entity;
    }

    /**
     * Convert a SchoolClass to a JSON which can be sent to the server.
     */
    private convert(schoolClass: SchoolClass): SchoolClass {
        const copy: SchoolClass = Object.assign({}, schoolClass);
        return copy;
    }
}
