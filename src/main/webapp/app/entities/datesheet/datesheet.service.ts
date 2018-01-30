import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Datesheet } from './datesheet.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DatesheetService {

    private resourceUrl =  SERVER_API_URL + 'api/datesheets';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(datesheet: Datesheet): Observable<Datesheet> {
        const copy = this.convert(datesheet);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(datesheet: Datesheet): Observable<Datesheet> {
        const copy = this.convert(datesheet);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Datesheet> {
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
     * Convert a returned JSON object to Datesheet.
     */
    private convertItemFromServer(json: any): Datesheet {
        const entity: Datesheet = Object.assign(new Datesheet(), json);
        entity.date = this.dateUtils
            .convertDateTimeFromServer(json.date);
        return entity;
    }

    /**
     * Convert a Datesheet to a JSON which can be sent to the server.
     */
    private convert(datesheet: Datesheet): Datesheet {
        const copy: Datesheet = Object.assign({}, datesheet);

        copy.date = this.dateUtils.toDate(datesheet.date);
        return copy;
    }
}