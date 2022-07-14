import { Status } from "./status";
import { Type } from "./type";
import { User } from "./user";

export class Reimbursement {
    public id: number;
    public submitted: Date;
    public resolved: Date;
    public author: User;
    public resolver: User;
    public receipt: Blob;
    public description: string;
    public type: Type;
    public status: Status;

    constructor(id: number, submitted: Date, resolved: Date, author: User, resolver: User, receipt: Blob, description: string, type: Type, status: Status) {
        this.id = id;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.receipt = receipt;
        this.description = description;
        this.type = type;
        this.status = status;
    }

}