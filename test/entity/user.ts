
import {GroupMember} from './groupmember'
import {Login} from './login'
import {Member} from './member'
import {Token} from './token'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_user",'tunnel')
export class User extends BaseEntity{
	@Id()
	@Column({
		name:'user_id',
		type:'int',
		nullable:false
	})
	userId:number;

	@Column({
		name:'user_name',
		type:'string',
		nullable:true
	})
	userName:string;

	@Column({
		name:'user_pwd',
		type:'string',
		nullable:true
	})
	userPwd:string;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	enabled:number;

	@OneToMany({entity:'GroupMember',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',lazyFetch:true})
	groupMembers:Array<GroupMember>;

	@OneToMany({entity:'Login',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',lazyFetch:true})
	logins:Array<Login>;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',lazyFetch:true})
	members:Array<Member>;

	@OneToMany({entity:'Token',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',lazyFetch:true})
	tokens:Array<Token>;

}