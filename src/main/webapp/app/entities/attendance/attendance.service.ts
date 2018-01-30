import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Attendance } from './attendance.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AttendanceService {

    private resourceUrl =  SERVER_API_URL + 'api/attendances';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(attendance: Attendance): Observable<Attendance> {
        const copy = this.convert(attendance);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(attendance: Attendance): Observable<Attendance> {
        const copy = this.convert(attendance);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Attendance> {
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
     * Convert a returned JSON object to Attendance.
     */
    private convertItemFromServer(json: any): Attendance {
        const entity: Attendance = Object.assign(new Attendance(), json);
        entity.date = this.dateUtils
            .convertDateTimeFromServer(json.date);
        return entity;
    }

    /**
     * Convert a Attendance to a JSON which can be sent to the server.
     */
    private convert(attendance: Attendance): Attendance {
        const copy: Attendance = Object.assign({}, attendance);

        copy.date = this.dateUtils.toDate(attendance.date);
        return copy;
    }
}
