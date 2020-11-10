
import {Authority} from './authority'
import {Group} from './group'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_group_authority",'tunnel')
export class GroupAuthority extends BaseEntity{
	@Id()
	@Column({
		name:'group_authority_id',
		type:'int',
		nullable:false
	})
	groupAuthorityId:number;

	@ManyToOne({entity:GroupAuthority,lazyFetch:true})
	@JoinColumn({name:'authority',refName:'authority'})
	authority:Authority;

	@ManyToOne({entity:GroupAuthority,lazyFetch:true})
	@JoinColumn({name:'group_id',refName:'group_id'})
	group:Group;

}