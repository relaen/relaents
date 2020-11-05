import { Entity, Id, Column } from "../../core/decorator/decorator";
import { BaseEntity } from "../../core/entity";

@Entity("t_authority" ,"tunnel" )
export class Authority extends BaseEntity{
    @Id()
    @Column({
        type:'int', 
        nullable:false,
        name:"authority_id"
    })
    authorityId:number;
        

    @Column({
        type:'string', 
        nullable:false,
        length:50,
        name:"authority"
    })
    authority:string;

    // @OneToMany(()=>GroupAuthority, (groupAuthority: GroupAuthority)=>groupAuthority.authority,{ onDelete: 'CASCADE' ,onUpdate: 'CASCADE' })
    // tGroupAuthoritys:GroupAuthority[];
    
    // @OneToMany(()=>ResourceAuthority, (resourceAuthority: ResourceAuthority)=>resourceAuthority.authority,{ onDelete: 'CASCADE' ,onUpdate: 'CASCADE' })
    // resourceAuthorities:ResourceAuthority[];

    // @ManyToMany(type=>Group,group=>group.authorities)
    // groups:Group[];

    // @ManyToMany(type=>Resource,resource=>resource.authorities)
    // resources:Resource[];
}
