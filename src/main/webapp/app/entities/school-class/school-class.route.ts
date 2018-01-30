import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SchoolClassComponent } from './school-class.component';
import { SchoolClassDetailComponent } from './school-class-detail.component';
import { SchoolClassPopupComponent } from './school-class-dialog.component';
import { SchoolClassDeletePopupComponent } from './school-class-delete-dialog.component';

export const schoolClassRoute: Routes = [
    {
        path: 'school-class',
        component: SchoolClassComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SchoolClasses'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'school-class/:id',
        component: SchoolClassDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SchoolClasses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const schoolClassPopupRoute: Routes = [
    {
        path: 'school-class-new',
        component: SchoolClassPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SchoolClasses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'school-class/:id/edit',
        component: SchoolClassPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SchoolClasses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'school-class/:id/delete',
        component: SchoolClassDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SchoolClasses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
