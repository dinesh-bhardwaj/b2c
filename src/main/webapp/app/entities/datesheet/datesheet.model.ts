import { BaseEntity } from './../../shared';

export class Datesheet implements BaseEntity {
    constructor(
        public id?: number,
        public exam?: string,
        public image?: string,
        public date?: any,
        public schoolClass?: BaseEntity,
        public section?: BaseEntity,
    ) {
    }
}
