import { BaseEntity, User } from './../../shared';

export const enum ProfileSelection {
    'STUDENT',
    'TEACHER',
    'ADMIN'
}

export const enum GenderSelection {
    'MALE',
    'FEMALE'
}

export const enum ReligionField {
    'HINDU',
    'MUSLIM',
    'SIKH',
    'CHRISTIAN',
    'BUDDHIST',
    'JAIN',
    'OTHERS'
}

export const enum CategoryField {
    'GENERAL',
    'SC',
    'ST',
    'OBC'
}

export const enum PreviousClassField {
    'PRE_PRIMARY',
    'FIRST',
    'SECOND',
    'THIRD',
    'FOUTH',
    'FIFTH',
    'SIXTH',
    'SEVENTH',
    'EIGHTH',
    'NINETH',
    'TENTH',
    'ELEVENTH',
    'TWELVETH',
    'OTHERS'
}

export const enum Previousyear {
    'SAME_SCHOOL',
    'ANOTHER_SCHOOL',
    'AANGANWADI_ECCE',
    'NONE'
}

export const enum Disability {
    'BLINDNESS',
    'VISUAL_LOW_VISION'
}

export const enum YESNOSELECTION {
    'YES',
    'NO'
}

export const enum NAYESNOSELECTION {
    'NA',
    'YES',
    'NO'
}

export class Student implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public fatherName?: string,
        public motherName?: string,
        public dob?: any,
        public schoolName?: string,
        public admissionNumber?: string,
        public rollNo?: number,
        public email?: string,
        public address?: string,
        public mobile?: string,
        public phone?: string,
        public username?: string,
        public password?: string,
        public profile?: ProfileSelection,
        public image?: string,
        public gender?: GenderSelection,
        public locality?: string,
        public aadhar?: string,
        public dateOfAdmission?: any,
        public religion?: ReligionField,
        public category?: CategoryField,
        public previousClass?: PreviousClassField,
        public previousYear?: Previousyear,
        public disability?: Disability,
        public bpl?: YESNOSELECTION,
        public disadvantage?: NAYESNOSELECTION,
        public transport?: NAYESNOSELECTION,
        public escort?: NAYESNOSELECTION,
        public hostel?: NAYESNOSELECTION,
        public training?: NAYESNOSELECTION,
        public book?: NAYESNOSELECTION,
        public uniformsets?: NAYESNOSELECTION,
        public homeless?: NAYESNOSELECTION,
        public freeEducation?: NAYESNOSELECTION,
        public cwsn?: NAYESNOSELECTION,
        public disease?: string,
        public precautions?: string,
        public active?: any,
        public isDeleted?: boolean,
        public userId?: User,
        public schoolClass?: BaseEntity,
        public section?: BaseEntity,
        public session?: BaseEntity,
    ) {
        this.isDeleted = false;
    }
}
