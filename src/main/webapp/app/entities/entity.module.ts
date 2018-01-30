import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { B2CStudentModule } from './student/student.module';
import { B2CAttendanceModule } from './attendance/attendance.module';
import { B2CSessionModule } from './session/session.module';
import { B2CSchoolClassModule } from './school-class/school-class.module';
import { B2CDatesheetModule } from './datesheet/datesheet.module';
import { B2CDownloadsModule } from './downloads/downloads.module';
import { B2CSectionModule } from './section/section.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        B2CStudentModule,
        B2CAttendanceModule,
        B2CSessionModule,
        B2CSchoolClassModule,
        B2CDatesheetModule,
        B2CDownloadsModule,
        B2CSectionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CEntityModule {}
