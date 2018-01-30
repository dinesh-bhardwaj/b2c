import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AttendanceComponent } from './attendance.component';
import { AttendanceDetailComponent } from './attendance-detail.component';
import { AttendancePopupComponent } from './attendance-dialog.component';
import { AttendanceDeletePopupComponent } from './attendance-delete-dialog.component';

export const attendanceRoute: Routes = [
    {
        path: 'attendance',
        component: AttendanceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'attendance/:id',
        component: AttendanceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attendancePopupRoute: Routes = [
    {
        path: 'attendance-new',
        component: AttendancePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attendance/:id/edit',
        component: AttendancePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attendance/:id/delete',
        component: AttendanceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
