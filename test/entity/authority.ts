
import {GroupAuthority} from './groupauthority'
import {ResourceAuthority} from './resourceauthority'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_authority",'tunnel')
export class Authority extends BaseEntity{
	@Id()
	@Column({
		name:'authority',
		type:'string',
		nullable:false
	})
	authority:string;

	@OneToMany({entity:'GroupAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'authority',lazyFetch:true})
	groupAuthoritys:Array<GroupAuthority>;

	@OneToMany({entity:'ResourceAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'authority',lazyFetch:true})
	resourceAuthoritys:Array<ResourceAuthority>;

}