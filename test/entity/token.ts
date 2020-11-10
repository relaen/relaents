
import {User} from './user'
import { Entity, Id, ManyToOne, JoinColumn, Column } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_token",'tunnel')
export class Token extends BaseEntity{
	@Id()
	@ManyToOne({entity:Token,lazyFetch:true})
	@JoinColumn({name:'user_id',refName:'user_id'})
	user:User;

	@Column({
		name:'token',
		type:'string',
		nullable:true
	})
	token:string;

	@Column({
		name:'create_time',
		type:'int',
		nullable:true
	})
	createTime:number;

	@Column({
		name:'disable_time',
		type:'int',
		nullable:true
	})
	disableTime:number;

}