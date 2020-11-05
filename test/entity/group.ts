import { Entity, Id, Column, OneToMany } from "../../core/decorator/decorator";

import { BaseEntity } from "../../core/entity";
import { GroupAuthority } from "./groupauthority";

// import { GroupAuthority } from "./groupauthority";

@Entity("t_group","tunnel")
export class Group  extends BaseEntity{
    @Id()
    @Column({ 
        type:'bigint',
        nullable:false,
        name:"group_id"
    })
    groupId:number;
 
    @Column({
        type:'string',
        nullable:true,
        length:50,
        name:"group_name"
    })
    groupName:string | null;
    
    @Column({
        type:'string', 
        nullable:true,
        length:200,
        name:"remarks"
    })
    remarks:string | null;
    
    @OneToMany({entity:GroupAuthority})
    groupAuthorities:GroupAuthority[];
    
    // @ManyToMany(type=>User,user=>user.groups)
    // users:User[];

    // @ManyToMany(type=>Authority,authority=>authority.groups)
    // @JoinTable()
    // authorities:Promise<Authority[]>;
}
