
import {GroupAuthority} from './groupauthority'
import {GroupMember} from './groupmember'
import {GroupMenu} from './groupmenu'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_group",'tunnel')
export class Group extends BaseEntity{
	@Id()
	@Column({
		name:'group_id',
		type:'int',
		nullable:false
	})
	groupId:number;

	@Column({
		name:'group_name',
		type:'string',
		nullable:true
	})
	groupName:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'GroupAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',lazyFetch:true})
	groupAuthoritys:Array<GroupAuthority>;

	@OneToMany({entity:'GroupMember',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',lazyFetch:true})
	groupMembers:Array<GroupMember>;

	@OneToMany({entity:'GroupMenu',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',lazyFetch:true})
	groupMenus:Array<GroupMenu>;

}