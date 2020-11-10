
import {Road} from './road'
import {FuncCompany} from './funccompany'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_road_func_rel",'tunnel')
export class RoadFuncRel extends BaseEntity{
	@Id()
	@Column({
		name:'road_func_rel_id',
		type:'int',
		nullable:false
	})
	roadFuncRelId:number;

	@ManyToOne({entity:RoadFuncRel,lazyFetch:true})
	@JoinColumn({name:'road_id',refName:'road_id'})
	road:Road;

	@ManyToOne({entity:RoadFuncRel,lazyFetch:true})
	@JoinColumn({name:'func_company_id',refName:'func_company_id'})
	funcCompany:FuncCompany;

}