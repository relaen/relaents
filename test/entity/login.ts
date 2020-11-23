import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {User} from './user'

@Entity("t_login",'tunnel')
export class Login extends BaseEntity{
	@Id()
	@Column({
		name:'login_id',
		type:'int',
		nullable:false
	})
	private loginId:number;

	@ManyToOne({entity:'User',eager:false})
	@JoinColumn({name:'user_id',refName:'user_id'})
	private user:User;

	@Column({
		name:'login_time',
		type:'int',
		nullable:true
	})
	private loginTime:number;

	@Column({
		name:'login_ip',
		type:'string',
		nullable:true
	})
	private loginIp:string;

	public getLoginId():number{
		return this.loginId;
	}
	public setLoginId(value:number){
		this.loginId = value;
	}

	public getUser():User{
		return this.user;
	}
	public setUser(value:User){
		this.user = value;
	}

	public getLoginTime():number{
		return this.loginTime;
	}
	public setLoginTime(value:number){
		this.loginTime = value;
	}

	public getLoginIp():string{
		return this.loginIp;
	}
	public setLoginIp(value:string){
		this.loginIp = value;
	}

}