
import {User} from './user'
import {Group} from './group'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_group_member",'tunnel')
export class GroupMember extends BaseEntity{
	@Id()
	@Column({
		name:'group_member_id',
		type:'int',
		nullable:false
	})
	groupMemberId:number;

	@ManyToOne({entity:GroupMember,lazyFetch:true})
	@JoinColumn({name:'user_id',refName:'user_id'})
	user:User;

	@ManyToOne({entity:GroupMember,lazyFetch:true})
	@JoinColumn({name:'group_id',refName:'group_id'})
	group:Group;

}