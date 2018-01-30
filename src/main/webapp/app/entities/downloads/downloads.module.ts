import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { B2CSharedModule } from '../../shared';
import {
    DownloadsService,
    DownloadsPopupService,
    DownloadsComponent,
    DownloadsDetailComponent,
    DownloadsDialogComponent,
    DownloadsPopupComponent,
    DownloadsDeletePopupComponent,
    DownloadsDeleteDialogComponent,
    downloadsRoute,
    downloadsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...downloadsRoute,
    ...downloadsPopupRoute,
];

@NgModule({
    imports: [
        B2CSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DownloadsComponent,
        DownloadsDetailComponent,
        DownloadsDialogComponent,
        DownloadsDeleteDialogComponent,
        DownloadsPopupComponent,
        DownloadsDeletePopupComponent,
    ],
    entryComponents: [
        DownloadsComponent,
        DownloadsDialogComponent,
        DownloadsPopupComponent,
        DownloadsDeleteDialogComponent,
        DownloadsDeletePopupComponent,
    ],
    providers: [
        DownloadsService,
        DownloadsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CDownloadsModule {}
