import {Role} from './role';

export class User {
    public id: number;
    public username: string;
    public firstName: string;
    public lastName: string;
    public role: Role;

    constructor(id: number, username: string, firstName: string, lastName: string, role: Role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}