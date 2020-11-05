import {Authority} from "./authority";
import {Group} from "./group";
import { BaseEntity } from "../../core/entity";
import { Entity, Column, ManyToOne, Id, JoinColumn } from "../../core/decorator/decorator";
import { EFkConstraint } from "../../core/entitydefine";


@Entity("t_group_authority" ,"tunnel")
// @Index("FK_GROUPAUTH_REF_AUTH",["authority",])
// @Index("FK_GROUP_AU_REF_GROUP",["group",])
export class GroupAuthority extends BaseEntity{
    @Id()
    @Column({
        type:"int", 
        nullable:false,
        name:"group_authority_id"
    })
    groupAuthorityId:number;
    
    @ManyToOne({entity:Authority,onDelete:EFkConstraint.CASCADE,onUpdate: EFkConstraint.CASCADE,lazyFetch:false})
    @JoinColumn({name:'authority_id',refName:'authorty_id'})
    authority:Authority;
   
    @ManyToOne({entity:Group ,onDelete: 'CASCADE',onUpdate: 'CASCADE' ,lazyFetch:false})
    @JoinColumn({name:'group_id',refName:'group_id'})
    group:Group;
}
