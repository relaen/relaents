import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {GroupMember} from './groupmember'
import {Login} from './login'
import {Member} from './member'
import {Token} from './token'

@Entity("t_user",'tunnel')
export class User extends BaseEntity{
	@Id()
	@Column({
		name:'user_id',
		type:'int',
		nullable:false
	})
	private userId:number;

	@Column({
		name:'user_name',
		type:'string',
		nullable:true
	})
	private userName:string;

	@Column({
		name:'user_pwd',
		type:'string',
		nullable:true
	})
	private userPwd:string;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	private enabled:number;

	@OneToMany({entity:'GroupMember',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',eager:false})
	private groupMembers:Array<GroupMember>;

	@OneToMany({entity:'Login',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',eager:false})
	private logins:Array<Login>;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',eager:false})
	private members:Array<Member>;

	@OneToMany({entity:'Token',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'user',eager:false})
	private tokens:Array<Token>;

	public getUserId():number{
		return this.userId;
	}
	public setUserId(value:number){
		this.userId = value;
	}

	public getUserName():string{
		return this.userName;
	}
	public setUserName(value:string){
		this.userName = value;
	}

	public getUserPwd():string{
		return this.userPwd;
	}
	public setUserPwd(value:string){
		this.userPwd = value;
	}

	public getEnabled():number{
		return this.enabled;
	}
	public setEnabled(value:number){
		this.enabled = value;
	}

	public getGroupMembers():Array<GroupMember>{
		return this.groupMembers;
	}
	public setGroupMembers(value:Array<GroupMember>){
		this.groupMembers = value;
	}

	public getLogins():Array<Login>{
		return this.logins;
	}
	public setLogins(value:Array<Login>){
		this.logins = value;
	}

	public getMembers():Array<Member>{
		return this.members;
	}
	public setMembers(value:Array<Member>){
		this.members = value;
	}

	public getTokens():Array<Token>{
		return this.tokens;
	}
	public setTokens(value:Array<Token>){
		this.tokens = value;
	}

}