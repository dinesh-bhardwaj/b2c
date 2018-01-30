import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { DatesheetComponent } from './datesheet.component';
import { DatesheetDetailComponent } from './datesheet-detail.component';
import { DatesheetPopupComponent } from './datesheet-dialog.component';
import { DatesheetDeletePopupComponent } from './datesheet-delete-dialog.component';

export const datesheetRoute: Routes = [
    {
        path: 'datesheet',
        component: DatesheetComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Datesheets'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'datesheet/:id',
        component: DatesheetDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Datesheets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const datesheetPopupRoute: Routes = [
    {
        path: 'datesheet-new',
        component: DatesheetPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Datesheets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'datesheet/:id/edit',
        component: DatesheetPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Datesheets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'datesheet/:id/delete',
        component: DatesheetDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Datesheets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
