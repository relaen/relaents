
import {Authority} from './authority'
import {Resource} from './resource'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_resource_authority",'tunnel')
export class ResourceAuthority extends BaseEntity{
	@Id()
	@Column({
		name:'resource_authority_id',
		type:'int',
		nullable:false
	})
	resourceAuthorityId:number;

	@ManyToOne({entity:ResourceAuthority,lazyFetch:true})
	@JoinColumn({name:'authority',refName:'authority'})
	authority:Authority;

	@ManyToOne({entity:ResourceAuthority,lazyFetch:true})
	@JoinColumn({name:'resource_id',refName:'resource_id'})
	resource:Resource;

}