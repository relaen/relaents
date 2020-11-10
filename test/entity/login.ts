
import {User} from './user'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_login",'tunnel')
export class Login extends BaseEntity{
	@Id()
	@Column({
		name:'login_id',
		type:'int',
		nullable:false
	})
	loginId:number;

	@ManyToOne({entity:Login,lazyFetch:true})
	@JoinColumn({name:'user_id',refName:'user_id'})
	user:User;

	@Column({
		name:'login_time',
		type:'int',
		nullable:true
	})
	loginTime:number;

	@Column({
		name:'login_ip',
		type:'string',
		nullable:true
	})
	loginIp:string;

}