import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {User} from './user'

@Entity("t_token",'tunnel')
export class Token extends BaseEntity{
	@Id()
	@ManyToOne({entity:'User',eager:false})
	@JoinColumn({name:'user_id',refName:'user_id'})
	private user:User;

	@Column({
		name:'token',
		type:'string',
		nullable:true
	})
	private token:string;

	@Column({
		name:'create_time',
		type:'int',
		nullable:true
	})
	private createTime:number;

	@Column({
		name:'disable_time',
		type:'int',
		nullable:true
	})
	private disableTime:number;

	public getUser():User{
		return this.user;
	}
	public setUser(value:User){
		this.user = value;
	}

	public getToken():string{
		return this.token;
	}
	public setToken(value:string){
		this.token = value;
	}

	public getCreateTime():number{
		return this.createTime;
	}
	public setCreateTime(value:number){
		this.createTime = value;
	}

	public getDisableTime():number{
		return this.disableTime;
	}
	public setDisableTime(value:number){
		this.disableTime = value;
	}

}